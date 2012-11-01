<#-- @ftlvariable name="" type="org.multibit.store.views.PublicHomeView" -->
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
          <a href="/">Home</a> <span class="divider">/</span>
        </li>
        <li>
          <a href="/listings">Books</a> <span class="divider">/</span>
        </li>
        <li class="active">
          <a href="/category">All</a>
        </li>
      </ul>

    <#list model.promotionalItems as item>
      <div class="row">
        <div class="span1">
          <a href="${item.optionalProperties["item self"]}"><img alt="${item.optionalProperties.title!"Unknown"?html}"
                                                                 src="${item.optionalProperties.image_thumbnail_uri!"/images/book.jpg"}"></a>
        </div>

        <div class="span6">
          <a href="item"><h5>${item.optionalProperties.title!"Unknown"?html}</h5></a>

          <p>${item.optionalProperties.summary!"Unknown"?html}</p>
        </div>

        <div class="span1">
          <p>${model.cart.currencySymbol}&nbsp;${item.optionalProperties.price!"10"?html}</p>
        </div>

        <div class="span2">
          <form id="item-${item.SKU}" action="/cart" method="post">
            <input type="hidden" name="${item.SKU}" value="1" />
            <p><button class="btn btn-primary" type="submit">Add to cart</button></p>
          </form>

          <p><a class="btn btn-link" href="/wishlist">Add to Wish List</a></p>

          <p><a class="btn btn-link" href="/compare">Add to Compare</a></p>
        </div>
      </div>
      <hr/>
    </#list>

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