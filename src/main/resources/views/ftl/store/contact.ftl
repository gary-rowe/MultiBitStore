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

    <div class="span3">
      <h2>Our location</h2>

      <h3>Address:</h3>

      <p>MultiBit Ltd<br/>123 Nowhere Street<br/>Nowhere<br/>MU12 3ST</p>

      <div style="width:200px;height:200px">
        <iframe width="200" height="200" frameborder="0" scrolling="no" marginheight="0" marginwidth="0"
                src="http://maps.google.com/maps?f=q&source=s_q&hl=en&geocode=&q=London@51.508129,-0.128005&ie=UTF8&z=12&t=m&iwloc=near&output=embed"></iframe>
        <br>
        <table width="200" cellpadding="0" cellspacing="0" border="0">
          <tr>
            <td align="left">
              <small><a
                href="http://maps.google.com/maps?f=q&source=s_q&hl=en&geocode=&q=London@51.508129,-0.128005&ie=UTF8&z=12&t=m&iwloc=near">View
                Larger Map</a></small>
            </td>
            <td align="right">
              <small></small>
            </td>
          </tr>
        </table>
      </div>
      <br/><br/>

      <h3>Telephone:</h3>

      <p>0123 456 789</p>
    </div>
    <div class="span9">
      <div class="page-header">
        <h1>Contact us</h1>
      </div>

      <!-- Headings & Paragraph Copy -->
      <div class="row">


        <div class="span9">
          <form class="form-horizontal">
            <fieldset>
              <p>We've provided this form in case you don't have an email client handy. However, if you'd prefer
                to just email us then use <a href="mailto:info@multibit.org?subject=Enquiry">info@multibit.org</a>.</p><br/>

              <div class="control-group">
                <label for="focusedInput" class="control-label">First Name:</label>

                <div class="controls">
                  <input type="text" placeholder="your first name" id="focusedInput" class="input-xlarge focused span6">
                </div>
              </div>
              <div class="control-group">
                <label class="control-label">E-Mail Address:</label>

                <div class="controls">
                  <input type="text" placeholder="your email" class="input-xlarge span6">
                </div>
              </div>
              <div class="control-group">
                <label for="textarea" class="control-label">Enquiry:</label>

                <div class="controls">
                  <textarea rows="3" id="textarea" placeholder="What would you like to contact us about?"
                            class="input-xlarge span6"></textarea>
                </div>
              </div>

              <div class="span8">
                <button class="btn btn-primary pull-right" style="margin-right: 20px;" type="submit">Continue</button>
              </div>
            </fieldset>
          </form>

        </div>


        <!-- Misc Elements -->

      </div>
      <!-- /row -->

    </div>

  </div>

<#include "../includes/store/footer.ftl">

</div>
<!-- /container -->

<#include "../includes/store/cdn-scripts.ftl">

</body>
</html>