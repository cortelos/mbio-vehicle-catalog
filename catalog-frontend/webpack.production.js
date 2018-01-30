var webpack = require('webpack')

module.exports = [
  new webpack.DefinePlugin({
    "WEBPACK_BACKEND_URL": JSON.stringify("http://ec2-34-217-60-197.us-west-2.compute.amazonaws.com:9001/catalog/v1/")
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
