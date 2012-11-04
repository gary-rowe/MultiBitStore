<#-- @ftlvariable name="" type="org.multibit.store.views.PublicItemView" -->
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
    <#include "../includes/store/breadcrumb-item.ftl">

      <div class="row">
        <div class="span9">
          <h1>${model.item.optionalProperties.title!"Unknown"?html}</h1>
        </div>
      </div>
      <hr>

      <div class="row">
        <div class="span3">
          <img alt="" src="/images/book.jpg"/>


          <ul class="thumbnails">

            <li class="span1">
              <a href="#" class="thumbnail">
                <img src="/images/book-closed_50.jpg" alt="">
              </a>
            </li>

            <li class="span1">
              <a href="#" class="thumbnail">
                <img src="/images/excerpt_50.jpg" alt="">
              </a>
            </li>

            <li class="span1">
              <a href="#" class="thumbnail">
                <img src="/images/excerpt-2_50.jpg" alt="">
              </a>
            </li>

          </ul>

        </div>

        <div class="span6">

          <div class="span6">
            <address>
              <strong>Publisher:</strong> <span>${model.item.optionalProperties.publisher!"-"?html}</span><br/>
              <strong>ISBN:</strong> <span>${model.item.SKU!"-"?html}</span><br/>
              <strong>ISBN-13:</strong> <span>${model.item.GTIN!"-"?html}</span><br/>
              <strong>Availability:</strong> <span>${model.item.optionalProperties.availability!"-"?html}</span><br/>
            </address>
          </div>

          <div class="span6">
            <h2>
              <strong>Price: ${model.cart.currencySymbol}&nbsp;${model.item.price!"-"?html}</strong>
              <small>Ex Tax: ${model.cart.currencySymbol}&nbsp;${model.item.price!"-"?html}</small>
              <br/><br/>
            </h2>
          </div>

          <div class="span6">
            <form id="item-${model.item.SKU}" class="form-inline" action="/cart" method="post" >
              <div class="span3 no_margin_left">
                <label>Qty:</label>
                <input type="text" class="span1" placeholder="1" name="${model.item.SKU}" value="1">
                <button class="btn btn-primary" type="submit">Add to cart</button>
              </div>
              <div class="span1">
                - OR -
              </div>
              <div class="span2">
                <p><a href="#">Add to Wish List</a></p>

                <p><a href="compare">Add to Compare</a></p>
              </div>
            </form>
          </div>

          <div class="span6">

            <p>
              <input name="star1" type="radio" class="star"/>
              <input name="star1" type="radio" class="star"/>
              <input name="star1" type="radio" class="star"/>
              <input name="star1" type="radio" class="star"/>
              <input name="star1" type="radio" class="star"/>&nbsp;&nbsp;

              <a href="#">0 reviews</a> | <a href="#">Write a review</a></p>
          </div>


        </div>


      </div>
      <hr>
      <div class="row">
        <div class="span9">
          <div class="tabbable">
            <ul class="nav nav-tabs">
              <li class="active"><a href="#1" data-toggle="tab">Description</a></li>
              <li><a href="#2" data-toggle="tab">Reviews</a></li>
              <li><a href="#3" data-toggle="tab">Related products</a></li>
            </ul>
            <div class="tab-content">
              <div class="tab-pane active" id="1">${model.item.optionalProperties.description!"-"}</div>
              <div class="tab-pane" id="2">
                <p>There are no reviews for this product.</p>
              </div>
              <div class="tab-pane" id="3">
                <ul class="thumbnails related_products">

                  <li class="span2">
                    <div class="thumbnail">
                      <a href="item"><img alt="" src="http://placehold.it/220x180"/></a>

                      <div class="caption">
                        <a href="item"><h5>iPod Touch</h5></a> Price: &#36;50.00<br/><br/>
                      </div>
                    </div>
                  </li>

                  <li class="span2">
                    <div class="thumbnail">
                      <a href="item"><img alt="" src="http://placehold.it/220x180"/></a>

                      <div class="caption">
                        <a href="item"><h5>iPod Touch</h5></a> Price: &#36;50.00<br/><br/>
                      </div>
                    </div>
                  </li>

                  <li class="span2">
                    <div class="thumbnail">
                      <a href="item"><img alt="" src="http://placehold.it/220x180"/></a>

                      <div class="caption">
                        <a href="item"><h5>iPod Touch</h5></a> Price: &#36;50.00<br/><br/>
                      </div>
                    </div>
                  </li>

                  <li class="span2">
                    <div class="thumbnail">
                      <a href="item"><img alt="" src="http://placehold.it/220x180"/></a>

                      <div class="caption">
                        <a href="item"><h5>iPod Touch</h5></a> Price: &#36;50.00<br/><br/>
                      </div>
                    </div>
                  </li>


                </ul>
              </div>
            </div>
          </div>

        </div>
      </div>


    </div>

  </div><#include "../includes/store/footer.ftl">

</div>
<!-- /container -->

<#include "../includes/store/cdn-scripts.ftl">

</body>
</html>