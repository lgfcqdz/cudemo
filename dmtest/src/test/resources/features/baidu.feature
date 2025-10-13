Feature: function test for baidu

  @Smoke
  Scenario: search key value in baidu
    Given 用户打开百度首页
    When 用户在搜索框中输入 "Spring Boot"
    Then 页面标题应包含 "Spring Boot"

