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
    <#include "../includes/store/breadcrumb-listings.ftl">

      <div class="row">
        <div class="span9">
          <ul class="thumbnails">
          <#list model.promotionalItems as item>
            <li class="span3">
              <div class="thumbnail">
                <a href="/item/${item.SKU}"><img alt="${item.optionalProperties.title!"Unknown"?html}" src="${item.optionalProperties.image_thumbnail_uri!"/images/book.jpg"}"></a>

                <div class="caption">
                  <a href="/item/${item.SKU}"><h5>${item.optionalProperties.title!"Unknown"?html}</h5></a>
                </div>
              </div>
            </li>
          </#list>
          </ul>
        </div>
      </div>

<p>${html}</p>

    </div>

  </div>

<#include "../includes/store/footer.ftl">

</div>
<!-- /container -->

<#include "../includes/store/cdn-scripts.ftl">

</body>
</html>