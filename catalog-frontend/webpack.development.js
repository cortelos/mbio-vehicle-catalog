var webpack = require('webpack')

module.exports = [
  new webpack.DefinePlugin({
  "WEBPACK_BACKEND_URL": JSON.stringify("http://localhost:8080/catalog/v1/")
})]
