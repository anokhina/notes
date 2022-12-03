const mode = "development";

module.exports = {
    mode: mode,
    entry: './src/app.ts',
    output: {
        filename: 'bundle.js',
        path: __dirname
    },
    module: {
        rules: [
            {
                test: /\.tsx?$/,
                loader: 'ts-loader',
                exclude: /node_modules/,
            },
            {
                test: /\.css$/i,
                use: ["style-loader", "css-loader"],
            },
            {
                test: /\.(jpe?g|png|gif)$/i, 
                use: ["file-loader"]  
            }
        ]
    },
    resolve: {
        extensions: [".tsx", ".ts", ".js"]
    },
};
