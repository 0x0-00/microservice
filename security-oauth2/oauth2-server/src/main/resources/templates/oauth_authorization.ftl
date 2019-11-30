<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>Title</title>
</head>
<body>

<div class="container">

    <h3>${clientId}请求授权，该应用将获取你的以下信息</h3>

    <p>昵称，头像和性别</p>

    授权后表明你已同意 <a href="#boot" style="color: #E9686B">OAUTH-BOOT 服务协议</a>

    <form method="post" action="/oauth/authorize">

        <input type="hidden" name="user_oauth_approval" value="true">

        <div>
            <#list scopes as scope>
                 <input type="radio" name="scope.${scope}" value="true"/>
            </#list>
        </div>

        <input name="authorize" value="Authorize" type="submit">

    </form>
</div>
</body>
</html>