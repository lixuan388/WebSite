var layuiTableName='WebSite.layui'

layui.cache.tableName =layuiTableName;
var config = layui.data(layuiTableName).config;
if (config) {
  if (config.ContextPath) {
    layui.cache.base = config.ContextPath + '/layui/';
  }
  if (config.ContextPath) {
    layui.cache.ContextPath = config.ContextPath ;
  }
  if (config.Version) {
    layui.cache.version = config.Version ;
  }

  layui.cache.tableName =layuiTableName;
}
