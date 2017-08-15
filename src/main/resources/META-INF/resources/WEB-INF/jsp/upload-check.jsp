<!DOCTYPE html>
<html>
<head>
    <title>Image Uploading Form</title>
</head>

<body>
<h3>File Upload:</h3>
Select an image to upload and recognize: <br/>
<form action="/recognize" method="post"
      enctype="multipart/form-data">
    <input type="file" name="image" size="50"/>
    <br/>
    <input type="submit" value="Upload File"/>
</form>
</body>

</html>