<!DOCTYPE html>
<html lang="en">
<head>
<#include "../includes/head.ftl">
</head>

<body>

<div class="container">
<#include "../includes/header.ftl">
<#include "../includes/navbar.ftl">
  <div class="row">

    <div class="span12">
    <#include "../includes/breadcrumb-account.ftl">

      <div class="row">
        <div class="span9">
          <h1>Account login</h1>
        </div>
      </div>

      <hr/>

      <div class="row">

        <div class="span5 well">
          <h2>New Customers</h2>

          <p>By creating an account with our store, you will be able to move through the checkout process faster, store
            multiple shipping addresses, view and track your orders in your account and more.</p><br/>
          <a href="register" class="btn btn-primary pull-right">Create an account</a>
        </div>

        <div class="span5 well pull-right">
          <h2>Registered Customers</h2>

          <p>If you have an account with us, please log in.</p>

          <form class="form-horizontal" id="login" method="post"
                action="/account/login">
            <fieldset>
              <div class="control-group">
                <label for="username" class="control-label">Username</label>

                <div class="controls">
                  <input id="username" name="username" type="text" placeholder="Enter your username"
                         class="input-xlarge focused">
                </div>
              </div>
              <div class="control-group">
                <label class="control-label">Password</label>

                <div class="controls">
                  <input id="password" name="password" type="password" placeholder="Enter your password"
                         class="input-xlarge">
                </div>
              </div>

              <button class="btn btn-primary pull-right" type="submit">Login</button>
            </fieldset>
          </form>

        </div>

      </div>
    </div>

  </div>


<#include "../includes/footer.ftl">

</div>
<!-- /container -->

<#include "../includes/cdn-scripts.ftl">


</body>
</html>