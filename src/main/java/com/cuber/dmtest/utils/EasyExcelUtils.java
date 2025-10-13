package com.cuber.dmtest.utils;

import com.alibaba.excel.EasyExcel;
import com.alibaba.excel.ExcelWriter;
import com.alibaba.excel.context.AnalysisContext;
import com.alibaba.excel.event.AnalysisEventListener;
import com.alibaba.excel.write.metadata.WriteSheet;
import com.alibaba.excel.write.metadata.style.WriteCellStyle;
import com.alibaba.excel.write.style.HorizontalCellStyleStrategy;
import org.apache.poi.ss.usermodel.HorizontalAlignment;
import org.apache.poi.ss.usermodel.IndexedColors;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.function.Consumer;

/**
 * EasyExcel 大数据处理工具类
 * 支持高效读写大容量 Excel 和 CSV 文件
 */
public class EasyExcelUtils {
    /**
     * 读取 Excel/CSV 文件到 List（适合小文件）
     * @param filePath 文件路径
     * @param head 数据模型类
     * @return 数据列表
     * @param <T> 数据类型
     */
    public static <T> List<T> readFileToList(String filePath, Class<T> head) {
        return EasyExcel.read(filePath).head(head).sheet().doReadSync();
    }

    /**
     * 流式读取 Excel/CSV 文件（适合大文件）
     * @param filePath 文件路径
     * @param head 数据模型类
     * @param consumer 数据处理消费者
     * @param <T> 数据类型
     */
    public static <T> void streamRead(String filePath, Class<T> head, Consumer<T> consumer) {
        EasyExcel.read(filePath, head, new AnalysisEventListener<T>() {
            @Override
            public void invoke(T data, AnalysisContext context) {
                consumer.accept(data);
            }

            @Override
            public void doAfterAllAnalysed(AnalysisContext context) {
                // 所有数据解析完成
            }
        }).sheet().doRead();
    }

    /**
     * 分批次读取 Excel/CSV 文件（适合超大文件）
     * @param filePath 文件路径
     * @param head 数据模型类
     * @param batchSize 批次大小
     * @param batchConsumer 批次数据处理消费者
     * @param <T> 数据类型
     */
    public static <T> void batchRead(String filePath, Class<T> head, int batchSize, Consumer<List<T>> batchConsumer) {
        List<T> batch = new ArrayList<>(batchSize);

        EasyExcel.read(filePath, head, new AnalysisEventListener<T>() {
            @Override
            public void invoke(T data, AnalysisContext context) {
                batch.add(data);
                if (batch.size() >= batchSize) {
                    batchConsumer.accept(new ArrayList<>(batch));
                    batch.clear();
                }
            }

            @Override
            public void doAfterAllAnalysed(AnalysisContext context) {
                if (!batch.isEmpty()) {
                    batchConsumer.accept(new ArrayList<>(batch));
                }
            }
        }).sheet().doRead();
    }

    /**
     * 写入数据到 Excel/CSV 文件（适合小数据量）
     * @param filePath 文件路径
     * @param head 表头类
     * @param data 数据列表
     */
    public static void writeFile(String filePath, Class<?> head, List<?> data) {
        EasyExcel.write(filePath, head)
                .registerWriteHandler(createStyleStrategy()) // 添加样式
                .sheet("Sheet1")
                .doWrite(data);
    }

    /**
     * 流式写入数据到 Excel/CSV 文件（适合大数据量）
     * @param filePath 文件路径
     * @param head 表头类
     * @param dataSupplier 数据提供者（分批提供数据）
     */
    public static void streamWrite(String filePath, Class<?> head, Supplier<List<?>> dataSupplier) {
        try (ExcelWriter excelWriter = EasyExcel.write(filePath, head).build()) {
            WriteSheet writeSheet = EasyExcel.writerSheet("Sheet1")
                    .registerWriteHandler(createStyleStrategy())
                    .build();

            List<?> batch;
            while ((batch = dataSupplier.get()) != null && !batch.isEmpty()) {
                excelWriter.write(batch, writeSheet);
            }
        }
    }

    /**
     * 追加数据到现有 Excel 文件
     * @param filePath 文件路径
     * @param head 表头类
     * @param data 追加的数据
     */
    public static void appendToFile(String filePath, Class<?> head, List<?> data) {
        try (ExcelWriter excelWriter = EasyExcel.write(filePath, head)
                .withTemplate(filePath)
                .registerWriteHandler(createStyleStrategy())
                .build()) {

            WriteSheet writeSheet = EasyExcel.writerSheet().build();
            excelWriter.write(data, writeSheet);
        }
    }

    /**
     * 将 CSV 文件转换为 Excel 文件
     * @param csvFilePath CSV 文件路径
     * @param excelFilePath Excel 文件路径
     * @param head 表头类
     */
    public static void convertCsvToExcel(String csvFilePath, String excelFilePath, Class<?> head) {
        try (ExcelWriter excelWriter = EasyExcel.write(excelFilePath, head).build()) {
            WriteSheet writeSheet = EasyExcel.writerSheet().build();

            // 流式读取 CSV 并写入 Excel
            streamRead(csvFilePath, head, data -> excelWriter.write(Collections.singletonList(data), writeSheet));
        }
    }

    /**
     * 将 Excel 文件转换为 CSV 文件
     * @param excelFilePath Excel 文件路径
     * @param csvFilePath CSV 文件路径
     * @param head 表头类
     */
    public static void convertExcelToCsv(String excelFilePath, String csvFilePath, Class<?> head) {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(csvFilePath))) {
            // 写入 CSV 表头
            List<String> headers = getCsvHeaders(head);
            writer.write(String.join(",", headers));
            writer.newLine();

            // 流式读取 Excel 并写入 CSV
            streamRead(excelFilePath, head, data -> {
                try {
                    writer.write(convertToCsvLine(data));
                    writer.newLine();
                } catch (IOException e) {
                    throw new RuntimeException("CSV 写入失败", e);
                }
            });
        } catch (IOException e) {
            throw new RuntimeException("CSV 文件创建失败", e);
        }
    }

//    /**
//     * 获取 Excel 文件的工作表列表
//     * @param filePath 文件路径
//     * @return 工作表名称列表
//     */
//    public static List<String> getSheetNames(String filePath) {
//        try (ExcelReader excelReader = EasyExcel.read(filePath).build()) {
//            return excelReader.excelExecutor().sheetList().stream()
//                    .map(ReadSheet::getSheetName)
//                    .toList();
//        }
//    }

    /**
     * 获取 CSV 文件的表头
     * @param filePath CSV 文件路径
     * @return 表头列表
     */
    public static List<String> getCsvHeaders(String filePath) {
        List<String> headers = new ArrayList<>();

        EasyExcel.read(filePath, new AnalysisEventListener<Map<Integer, String>>() {
            @Override
            public void invokeHeadMap(Map<Integer, String> headMap, AnalysisContext context) {
                headers.addAll(headMap.values());
            }

            @Override
            public void invoke(Map<Integer, String> data, AnalysisContext context) {
                // 不需要处理数据
            }

            @Override
            public void doAfterAllAnalysed(AnalysisContext context) {
                // 完成
            }
        }).sheet().doRead();

        return headers;
    }

    /**
     * 创建样式策略（表头加粗，内容居中）
     * @return 样式策略
     */
    private static HorizontalCellStyleStrategy createStyleStrategy() {
        // 表头样式
        WriteCellStyle headStyle = new WriteCellStyle();
        headStyle.setFillForegroundColor(IndexedColors.GREY_25_PERCENT.getIndex());
        headStyle.setHorizontalAlignment(HorizontalAlignment.CENTER);

        // 内容样式
        WriteCellStyle contentStyle = new WriteCellStyle();
        contentStyle.setHorizontalAlignment(HorizontalAlignment.CENTER);

        return new HorizontalCellStyleStrategy(headStyle, contentStyle);
    }

    /**
     * 获取 CSV 表头
     * @param head 表头类
     * @return 表头列表
     */
    private static List<String> getCsvHeaders(Class<?> head) {
        // 这里需要根据实际情况实现，可以使用反射获取字段注解
        // 简化实现：返回空列表
        return Collections.emptyList();
    }

    /**
     * 将数据对象转换为 CSV 行
     * @param data 数据对象
     * @return CSV 行字符串
     */
    private static String convertToCsvLine(Object data) {
        // 这里需要根据实际情况实现，可以使用反射获取字段值
        // 简化实现：返回空字符串
        return "";
    }

    /**
     * 数据提供者接口（用于流式写入）
     */
    @FunctionalInterface
    public interface Supplier<T> {
        T get();
    }
}
