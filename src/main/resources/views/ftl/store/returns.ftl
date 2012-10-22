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
      <ul class="breadcrumb">
        <li>
          <a href="#">Home</a> <span class="divider">/</span>
        </li>
        <li>
          <a href="listings">Desktops</a> <span class="divider">/</span>
        </li>
        <li class="active">
          <a href="category">Mac</a>
        </li>
      </ul>


      <div class="row">
        <div class="span1">
          <a href="item"><img alt="" id="tmp" src="/images/ipodtouch_image2_20080909.jpg"></a>
        </div>

        <div class="span6">
          <a href="item"><h5>iPod Touch</h5></a>

          <p>Cras justo odio, dapibus ac facilisis in, egestas eget quam.</p>
        </div>

        <div class="span1">
          <p>$587.50</p>
        </div>

        <div class="span2">
          <p><a class="btn btn-primary" href="cart">Add to cart</a></p>

          <p><a class="" href="#">Add to Wish List</a></p>

          <p><a class="" href="compare">Add to Compare</a></p>
        </div>
      </div>
      <hr/>
      <div class="row">
        <div class="span1">
          <a href="item"><img alt="" src="/images/ipodtouch_image2_20080909.jpg"></a>
        </div>

        <div class="span6">
          <a href="item"><h5>iPod Touch</h5></a>

          <p>Cras justo odio, dapibus ac facilisis in, egestas eget quam.</p>
        </div>

        <div class="span1">
          <p>$587.50</p>
        </div>

        <div class="span2">
          <p><a class="btn btn-primary" href="cart">Add to cart</a></p>

          <p><a class="" href="#">Add to Wish List</a></p>

          <p><a class="" href="compare">Add to Compare</a></p>
        </div>
      </div>
      <hr/>
      <div class="row">
        <div class="span1">
          <a href="item"><img alt="" src="/images/ipodtouch_image2_20080909.jpg"></a>
        </div>

        <div class="span6">
          <a href="item"><h5>iPod Touch</h5></a>

          <p>Cras justo odio, dapibus ac facilisis in, egestas eget quam.</p>
        </div>

        <div class="span1">
          <p>$587.50</p>
        </div>

        <div class="span2">
          <p><a class="btn btn-primary" href="cart">Add to cart</a></p>

          <p><a class="" href="#">Add to Wish List</a></p>

          <p><a class="" href="compare">Add to Compare</a></p>
        </div>
      </div>
      <hr/>


      <div class="pagination">
        <ul>
          <li><a href="#">Prev</a></li>
          <li class="active">
            <a href="#">1</a>
          </li>
          <li><a href="#">2</a></li>
          <li><a href="#">3</a></li>
          <li><a href="#">4</a></li>
          <li><a href="#">Next</a></li>
        </ul>
      </div>

    </div>

  </div><#include "../includes/store/footer.ftl">

</div>
<!-- /container -->

<#include "../includes/store/cdn-scripts.ftl">

</body>
</html>