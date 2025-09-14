pipeline {
    agent any
    tools {
            // 指定在 Jenkins 全局工具配置中定义的 Git 名称
            git 'Default'
        }
    options {
        timeout(time: 30, unit: 'MINUTES')
        buildDiscarder(logRotator(numToKeepStr: '10'))
    }

    parameters {
        string(name: 'BRANCH_NAME', defaultValue: 'main', description: '要构建的分支名称') // 添加分支参数
        choice(name: 'TEST_TAGS', choices: ['@Smoke', '@Regression', '@All'], description: '选择测试标签')
    }

    environment {
        // 使用 Jenkins 提供的 WORKSPACE 环境变量
        BASE_DIR = "${env.ReportPath}\\target"  // Windows 使用反斜杠
        TEST_TAGS = "${params.TEST_TAGS}"
        REPORT_DIR = "${BASE_DIR}\\reports"  // Windows 使用反斜杠
        GIT_PATH = "D:\\Program Files\\Git\\bin\\git.exe"
    }

    stages {

        stage('Checkout') {
            steps {
                script {

                    try {
                        // 添加 Git 凭证信息
                        withCredentials([
                            usernamePassword(
                                credentialsId: 'cebed23e-84ed-4a10-b037-3b437e051553',
                                usernameVariable: 'GIT_USERNAME',
                                passwordVariable: 'GIT_TOKEN'
                            )
                        ]) {
                            // 使用带凭证的 Git URL
                            checkout([
                                $class: 'GitSCM',
                                branches: [[name: params.BRANCH_NAME]],
                                extensions: [],
                                userRemoteConfigs: [[
                                    url: "https://${GIT_USERNAME}:${GIT_TOKEN}@github.com/lgfcqdz/cudemo.git",
                                    credentialsId: 'cebed23e-84ed-4a10-b037-3b437e051553'
                                ]]
                            ])
                        }

                        // Windows 使用 bat 替代 sh
                   // 验证检出结果
                   bat "\"${GIT_PATH}\" branch -v"
                   bat "\"${GIT_PATH}\" log -1 --oneline"
                    } catch (Exception e) {
                        echo "check failed: ${e}"
                        error "check out failed: ${e}"  // 抛出错误，终止流水线
                    }
                }
            }
        }

        stage('Build') {
            steps {
                    bat """
                        set JAVA_TOOL_OPTIONS=-Dfile.encoding=UTF-8 -Dsun.jnu.encoding=UTF-8 ^
                                              --add-opens=jdk.compiler/com.sun.tools.javac.api=ALL-UNNAMED ^
                                              --add-opens=jdk.compiler/com.sun.tools.javac.file=ALL-UNNAMED ^
                                              --add-opens=jdk.compiler/com.sun.tools.javac.processing=ALL-UNNAMED ^
                                              --add-opens=jdk.compiler/com.sun.tools.javac.tree=ALL-UNNAMED ^
                                              --add-opens=jdk.compiler/com.sun.tools.javac.util=ALL-UNNAMED

                        set MAVEN_OPTS=-Dfile.encoding=UTF-8

                        mvn clean compile -Dmaven.compiler.forceJavacCompilerUse=true
                    """
                }
        }

        stage('Debug Info') {
            steps {
                script {
                    echo "TEST_TAGS: ${TEST_TAGS}"
                    echo "report_dir: ${REPORT_DIR}"
                    bat 'mvn --version'
                    bat 'java -version'
                    bat 'echo current directory: && cd'
                    bat 'dir /B'  // Windows 列出目录

                    // 创建报告目录 - Windows 方式
                    bat "mkdir ${REPORT_DIR}"
                }
            }
        }

        stage('Run Tests') {
            steps {
                script {
                    // 使用 bat 替代 sh
                    bat """
                        echo excute commit...
                        echo lable: ${TEST_TAGS}

                        mvn test ^
                            -Dtest=TestRunner ^
                            -Dcucumber.filter.tags="${TEST_TAGS}" ^
                            -Dcucumber.plugin="pretty,html:${REPORT_DIR}/report.html"
                    """
                }
            }
            post {
                always {
                        junit testResults: "**/target/surefire-reports/*.xml", allowEmptyResults: true
                        publishHTML target: [
                            allowMissing: true,
                            alwaysLinkToLastBuild: true,
                            keepAll: true,
                            reportDir: "${REPORT_DIR}",
                            reportFiles: 'report.html',
                            reportName: 'Cucumber Report'
                        ]
                    }
            }
        }
    }

    post {
        always {
            script {
                // 清理工作空间（保留报告）
                cleanWs(
                    cleanWhenAborted: true,
                    cleanWhenFailure: true,
                    cleanWhenNotBuilt: true,
                    cleanWhenUnstable: true,
                    deleteDirs: true,
                    notFailBuild: true
                )
            }
        }
        success {
            slackSend(color: 'good',
                     message: "✅ Tests Passed: ${env.JOB_NAME} #${env.BUILD_NUMBER}")
        }
        failure {
            slackSend(color: 'danger',
                     message: "❌ Tests Failed: ${env.JOB_NAME} #${env.BUILD_NUMBER}")
        }
    }
}