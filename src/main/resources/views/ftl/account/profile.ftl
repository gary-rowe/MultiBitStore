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

      <form class="form-horizontal">
        <fieldset>
          <div class="span6 no_margin_left">
            <strong>Your Personal Details</strong>
            <div class="control-group">
              <label for="first_name" class="control-label">First Name</label>

              <div class="controls docs-input-sizes">
                <input id="first_name" type="text" placeholder="" class="span4">
              </div>
            </div>
            <div class="control-group">
              <label for="last_name" class="control-label">Last Name</label>

              <div class="controls docs-input-sizes">
                <input id="last_name" type="text" placeholder="" class="span4">
              </div>
            </div>
            <div class="control-group">
              <label for="email_address" class="control-label">Email Address</label>

              <div class="controls docs-input-sizes">
                <input id="email_address"  type="text" placeholder="" class="span4">
              </div>
            </div>

            <div class="control-group">
              <label for="land_line" class="control-label">Telephone</label>

              <div class="controls docs-input-sizes">
                <input id="land_line" type="text" placeholder="" class="span4">
              </div>
            </div>
          </div>
          <div class="span6">
            <legend>Your Address</legend>
            <div class="control-group">
              <label class="control-label">Address</label>

              <div class="controls docs-input-sizes">
                <input type="text" placeholder="" class="span4">
              </div>
            </div>
            <div class="control-group">
              <label class="control-label">Address 2</label>

              <div class="controls docs-input-sizes">
                <input type="text" placeholder="" class="span4">
              </div>
            </div>
            <div class="control-group">
              <label class="control-label">City</label>

              <div class="controls docs-input-sizes">
                <input type="text" placeholder="" class="span4">
              </div>
            </div>
            <div class="control-group">
              <label class="control-label">ZIP</label>

              <div class="controls docs-input-sizes">
                <input type="text" placeholder="" class="span4">
              </div>
            </div>
            <div class="control-group">
              <label class="control-label">Country</label>

              <div class="controls docs-input-sizes">
                <input type="text" placeholder="" class="span4">
              </div>
            </div>
          </div>

          <div class="span12 no_margin_left">
            <legend>Your signin</legend>
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
            <div class="span8">
              <input type="checkbox" value="option1" name="optionsCheckboxList1"> I have read and agree to the Privacy
              Policy<br/>
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