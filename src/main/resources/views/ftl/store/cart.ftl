<#-- @ftlvariable name="" type="org.multibit.store.views.PublicCartView" -->
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

      <h1>Shopping Cart</h1><br/>

      <p class="text-info">You can remove items by setting their quantity to zero.</p>

      <form id="cart" action="/cart" method="post">
        <fieldset>
          <table class="table table-bordered table-striped">
            <thead>
            <tr>
              <th>Image</th>
              <th>Product Name</th>
              <th>Quantity</th>
              <th>Unit Price (${model.cart.currencySymbol})</th>
              <th>Tax (${model.cart.currencySymbol})</th>
              <th>Total (${model.cart.currencySymbol})</th>
            </tr>
            </thead>
            <tbody>
            <#if model.cart.isEmpty() == true >
            <tr>
              <td>Empty&nbsp;</td>
              <td>&nbsp;</td>
              <td>&nbsp;</td>
              <td>&nbsp;</td>
              <td>&nbsp;</td>
              <td><strong>$0.00</strong></td>
            </tr>
            <#else>
              <#list model.cart.cartItems as cartItem>
              <tr>
                <td class="muted center_text"><a href="${cartItem.item.optionalProperties["item self"]}"><img
                  alt="${cartItem.item.optionalProperties.title!"Unknown"?html}"
                  src="${cartItem.item.optionalProperties.image_thumbnail_uri!"/images/book.jpg"}"></a></td>
                <td>${cartItem.item.optionalProperties.title}</td>
                <td><input type="text" placeholder="${cartItem.quantity}" class="input-mini"
                           name="${cartItem.item.SKU}" value="${cartItem.quantity}"></td>
                <td>${cartItem.item.optionalProperties.price!"0"}</td>
                <td>${cartItem.quantity * 1.1 * 0.2}</td>
                <td>${cartItem.quantity * 1.1 * 1.2}</td>
              </tr>
              </#list>
            <tr>
              <td>&nbsp;</td>
              <td>&nbsp;</td>
              <td>&nbsp;</td>
              <td>&nbsp;</td>
              <td>&nbsp;</td>
              <td><strong>${model.cart.currencySymbol}&nbsp;${model.cart.priceTotal}</strong></td>
            </tr>
            </#if>
            </tbody>
          </table>


          <div class="accordion" id="accordion2">
            <div class="accordion-group">
              <div class="accordion-heading">

                <a class="accordion-toggle" data-toggle="collapse" data-parent="#accordion2" href="#collapseOne">
                  <h3>Apply discount code</h3>
                </a>
              </div>
              <div id="collapseOne" class="accordion-body collapse in">
                <div class="accordion-inner">
                  <div class="control-group">
                    <label for="input01" class="control-label">Discount code: </label>

                    <div class="controls">
                      <input type="text" id="input01" class="input-xlarge" placeholder="Enter your coupon here">

                      <p class="help-block">You can only use one discount code at a time</p>
                    </div>
                  </div>
                </div>
              </div>
            </div>

            <div class="accordion-group">
              <div class="accordion-heading">
                <a class="accordion-toggle" data-toggle="collapse" data-parent="#accordion2" href="#collapseTwo">
                  <h3>Use gift voucher</h3>
                </a>
              </div>
              <div id="collapseTwo" class="accordion-body collapse">
                <div class="accordion-inner">
                  <div class="control-group">
                    <label for="input01" class="control-label">Gift voucher: </label>

                    <div class="controls">
                      <input type="text" id="input01" class="input-xlarge" placeholder="Enter your gift voucher here">

                      <p class="help-block">You can use multiple gift vouchers at a time</p>
                    </div>
                  </div>
                </div>
              </div>
            </div>
          </div>

          <div class="row">
            <div class="span5">
              <button class="btn btn-info" type="submit">Update</button>
            </div>
            <div class="span2">
              <a href="/" class="btn btn-primary pull-right">Continue shopping</a>
            </div>
            <div class="span5">
              <a href="/checkout" class="btn btn-success pull-right">Checkout</a>
            </div>
          </div>
        </fieldset>
      </form>


    <#include "../includes/store/footer.ftl">

    </div>
    <!-- /container -->

  <#include "../includes/store/cdn-scripts.ftl">


</body>
</html>
