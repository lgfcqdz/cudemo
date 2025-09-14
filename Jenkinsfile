pipeline {
    agent any
    options {
        timeout(time: 30, unit: 'MINUTES')
        buildDiscarder(logRotator(numToKeepStr: '10'))
    }

    parameters {
        string(name: 'TEST_LABLES', defaultValue: '@Smoke', description: '标签类型')
        choice(name: 'TEST_TAGS', choices: ['@smoke', '@regression', '@all'], description: '选择测试标签')
        choice(name: 'TEST_ENV', choices: ['dextest', 'QA', 'UAT'], description: '选择测试环境')
        // 添加缺失的TEST_DIR参数（如果需要）
        // string(name: 'TEST_DIR', defaultValue: 'src/test/resources/features', description: '测试目录')
    }

    environment {
        // 使用params对象访问参数值
        TEST_LABLES = "${params.TEST_LABLES}"
        TEST_ENV = "${params.TEST_ENV}"
        TEST_TAGS = "${params.TEST_TAGS}"
        // 如果不需要TEST_DIR参数，可以移除或设置默认值
        // TEST_DIR = params.TEST_DIR ?: 'src/test/resources/features'
        REPORT_DIR = 'target/reports'  // 报告输出目录
    }

    stages {
        stage('Checkout') {
            steps {
                checkout scm  // 从SCM检出代码
            }
        }

        stage('Build') {
            steps {
                sh 'mvn clean compile'  // 编译项目
            }
        }

        stage('Run Tests') {
            steps {
                script {
                    // 创建报告目录（确保存在）
                    sh "mkdir -p ${REPORT_DIR} || true"

                    // 执行带标签的测试
                    sh """
                         mvn test \\
                             -Dtest=TestRunner \\
                             -Dcucumber.filter.tags="${TEST_LABLES}"
                    """
                }
            }
        }
    }

    post {
        always {
            // 收集报告（在清理前进行）
            publishHTML(target: [
                allowMissing: true,
                alwaysLinkToLastBuild: true,
                keepAll: true,
                reportDir: "${REPORT_DIR}",
                reportFiles: 'report.html',
                reportName: 'Test Report'
            ])

            junit "**/target/surefire-reports/*.xml"
            cucumber buildStatus: 'UNSTABLE',
                fileIncludePattern: '**/cucumber.json',
                trendsLimit: 10

            // 归档日志（如果有）
            archiveArtifacts artifacts: '**/logs/*.log', allowEmptyArchive: true

            // 最后清理工作空间
            cleanWs()
        }
        success {
            slackSend(color: 'good', message: "✅ Tests Passed: ${env.JOB_NAME} #${env.BUILD_NUMBER}")
        }
        failure {
            slackSend(color: 'danger', message: "❌ Tests Failed: ${env.JOB_NAME} #${env.BUILD_NUMBER}")
        }
    }
}