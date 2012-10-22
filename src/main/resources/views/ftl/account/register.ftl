<!DOCTYPE html>
<html lang="en">
<head>
<#include "../includes/store/head.ftl">
</head>

<body>

<div class="container">
<#include "../includes/store/header.ftl">
<#include "../includes/store/navbar.ftl">
  <div class="row">

    <div class="span12">
    <#include "../includes/store/breadcrumb-account.ftl">

      <div class="span12">
      <h1>Create an account</h1>

      <br/>

      <form class="form-horizontal" action="/account/register" method="POST">
        <fieldset>

          <div class="span12 no_margin_left">
            <h2>Your signin</h2>
            <div class="span6 no_margin_left">
              <div class="control-group">
                <label class="control-label">Username</label>

                <div class="controls docs-input-sizes">
                  <input type="text" placeholder="" class="span4">
                </div>
              </div>
            </div>
            <div class="span6">
              <div class="control-group">
                <label class="control-label">Password</label>

                <div class="controls docs-input-sizes">
                  <input type="text" placeholder="" class="span4">
                </div>
              </div>
              <div class="control-group">
                <label class="control-label">Confirm password</label>

                <div class="controls docs-input-sizes">
                  <input type="text" placeholder="" class="span4">
                </div>
              </div>
            </div>

          </div>

          <div class=" span12 no_margin_left">
            <hr>
            <h2>Agreements</h2>
            <div class="span8">
              <input type="checkbox" value="option1" name="optionsCheckboxList1"> I have read and agree to the <a href="/privacy" target="_blank">Privacy
              Policy</a> <br/>
              <input type="checkbox" value="option2" name="optionsCheckboxList2"> Subscribe to our newsletter

            </div>
            <div class="span3">
              <button class="btn btn-primary btn-large pull-right" type="submit">Register</button>
            </div>
            <hr>
          </div>
        </fieldset>
      </form>

    </div>

    <hr/>


  </div>

<#include "../includes/store/footer.ftl">

</div>
<!-- /container -->

<#include "../includes/store/cdn-scripts.ftl">

</body>
</html>