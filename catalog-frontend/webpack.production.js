var webpack = require('webpack')

module.exports = [
  new webpack.DefinePlugin({
    "WEBPACK_BACKEND_URL": JSON.stringify("http://jenkins.aws.local:9001/catalog/v1/")
  }),
  new webpack.DefinePlugin({
  'process.env': {
  }
  }),
  new webpack.optimize.UglifyJsPlugin({
    sourceMap: true,
    compress: {
      warnings: false
    }
  }),
  new webpack.LoaderOptionsPlugin({
    minimize: true
})]
