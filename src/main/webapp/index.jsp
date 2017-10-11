<html>
<head>
    <link rel="stylesheet" href="./css/bootstrap.min.css">
    <script src="./js/JsonProvider.js"></script>
    <script src="./js/FileDisplayer.js"></script>
</head>
<body>
    <div id='files' class='panel-body'></div>
    <script>
        const displayer = new FileDisplayer('/processed-files');
        setInterval(() => displayer.displayIn('files'), 2000);
    </script>
</body>
</html>
