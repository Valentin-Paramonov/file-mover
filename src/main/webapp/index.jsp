<html>
<head>
    <link rel="stylesheet" href="./css/bootstrap.min.css">
    <script src="./js/JsonProvider.js"></script>
    <script src="./js/FileDisplayer.js"></script>
</head>
<body>
<h2>Hello World!</h2>
<div id='files'>
</div>
<script>
    const displayer = new FileDisplayer('/processed-files');
    setInterval(() => displayer.displayIn('files'), 1000);
</script>
</body>
</html>
