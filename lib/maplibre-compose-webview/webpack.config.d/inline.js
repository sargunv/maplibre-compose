const HtmlWebpackPlugin = require('html-webpack-plugin');
const HtmlInlineScriptPlugin = require('html-inline-script-webpack-plugin');

config.plugins = [
  ...config.plugins,
  new HtmlWebpackPlugin({
    filename: config.output.library + '.html',
    templateContent: `
      <!DOCTYPE html>
      <html lang="en">
      <head>
        <title>Map</title>
        <meta charset='utf-8'>
        <meta name="viewport" content="width=device-width, initial-scale=1">
        <link rel='stylesheet' href='https://unpkg.com/maplibre-gl@4.7.1/dist/maplibre-gl.css'/>
        <style>
          body { margin: 0; padding: 0; }
          html, body { height: 100%; }
        </style>
      </head>
      <body>
      </body>
      </html>
    `
  }),
  new HtmlInlineScriptPlugin(),
];

config.optimization = {
  ...config.optimization,
  minimize: false,
};
