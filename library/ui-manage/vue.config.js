module.exports = {
  // 基本路径
  publicPath: './',
  // 输出文件目录
  outputDir: 'dist',
  // 放置生成的静态文件
  assetsDir: 'static',
  devServer: {
    hot: true,
    host: '0.0.0.0',
    port: 8080,
    open: false,
    proxy: {
      '/api': {
        ws: false,
        target: 'http://127.0.0.1:8853/',
        changeOrigin: true
      },
      '/auth/userjwt': {
        ws: false,
        target: 'http://127.0.0.1:8819/',
        changeOrigin: true
      }
    }
  }
}
