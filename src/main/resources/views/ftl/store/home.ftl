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
    <#include "../includes/store/left-sidebar.ftl">
    </div>
    <div class="span9">
    <#include "../includes/store/breadcrumb-listings.ftl">

      <div class="row">
        <div class="span9">
          <!-- start categories -->
          <!-- TODO Create and fill template from promotional items -->
          <ul class="thumbnails">
            <li class="span3">
              <div class="thumbnail">
                <a href="/listings"><img alt="" src="/images/cat_camera_photo.png"></a>

                <div class="caption">
                  <a href="/listings"><h5>Camera & Photo</h5></a>
                </div>
              </div>
            </li>
            <li class="span3">
              <div class="thumbnail">
                <a href="/listings"><img alt="" src="/images/cat_tv.jpg"></a>

                <div class="caption">
                  <a href="/listings"><h5>TV</h5></a>
                </div>
              </div>
            </li>
            <li class="span3">
              <div class="thumbnail">
                <a href="/listings"><img alt="" src="/images/cat_home_cinema.jpg"></a>

                <div class="caption">
                  <a href="/listings"><h5>Home Cinema</h5></a>
                </div>
              </div>
            </li>
            <li class="span3">
              <div class="thumbnail">
                <a href="/listings"><img alt="" src="/images/cat_audio.jpg"></a>

                <div class="caption">
                  <a href="/listings"><h5>Audio, MP3 & Accessories</h5></a>
                </div>
              </div>
            </li>
            <li class="span3">
              <div class="thumbnail">
                <a href="/listings"><img alt="" src="/images/cat_sat_nav.jpg"></a>

                <div class="caption">
                  <a href="/listings"><h5>Sat Nav & Car Electronics</h5></a>
                </div>
              </div>
            </li>
            <li class="span3">
              <div class="thumbnail">
                <a href="/listings"><img alt="" src="/images/cat_phone.png"></a>

                <div class="caption">
                  <a href="/listings"><h5>Phones & Accessories</h5></a>
                </div>
              </div>
            </li>
            <li class="span3">
              <div class="thumbnail">
                <a href="/listings"><img alt="" src="/images/cat_games.png"></a>

                <div class="caption">
                  <a href="/listings"><h5>PC & Video Games</h5></a>
                </div>
              </div>
            </li>
          </ul>
        </div>
        <!-- end categories -->
      </div>

    </div>

  </div>

<#include "../includes/store/footer.ftl">

</div>
<!-- /container -->

<#include "../includes/store/cdn-scripts.ftl">

</body>
</html>