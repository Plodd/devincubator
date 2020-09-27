<!DOCTYPE html>
<html>
    <head>
        <meta charset="UTF-8">
        <title>JSP</title>
    </head>
    <body>

        <form action="${pageContext.request.contextPath}/test" method="get">
            <output name="richest user">${user}</output>
            <input class="buttons" type="submit" name="button1" value="Get richest user" />

            <br><output name="accounts sum">${sum}</output>
            <input type="submit" name="button2" value="Get accounts sum" />
        </form>

    </body>
</html>