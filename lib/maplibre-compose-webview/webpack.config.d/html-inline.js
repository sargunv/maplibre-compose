const HtmlWebpackPlugin = require('html-webpack-plugin');
const HtmlInlineScriptPlugin = require('html-inline-script-webpack-plugin');

config.plugins = [
  ...config.plugins,
  new HtmlWebpackPlugin({
    filename: config.output.library + '.html',
    template: require('path').resolve(__dirname, "kotlin/index.html"),
  }),
  new HtmlInlineScriptPlugin(),
]
