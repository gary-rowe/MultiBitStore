<#-- @ftlvariable name="" type="org.multibit.store.views.PublicFreemarkerView" -->
<#include "theme-switcher.ftl">

<div class="row">
  <div class="span4 logo">
    <a href="/">
      <h1>MultiBit Store<sup>alpha-2</sup></h1>
    </a>
  </div>

  <div class="span8">

    <div class="row">
      <div class="span1">&nbsp;</div>
      <div class="span2">
        <h4>Currency</h4>
        <a href="#">USD</a> |
        <a href="#"><strong>BTC</strong></a> |
        <a href="#">GBP</a> |
        <a href="#">EUR</a>
      </div>
      <div class="span2">
      <#if model.cart?? >
        <a href="/cart"><h4>Shopping Cart (${model.cart.itemTotal!"?"})</h4></a>
        <a href="/cart">${model.cart.quantityTotal!"?"} item(s) - ${model.cart.localSymbol!"?"}${model.cart.localTotal!"?"}</a>
      <#else>
        <a href="/cart"><h4>Shopping Cart (0)</h4></a>
        <a href="/cart">0 item(s) - $0.00</a>
      </#if>
      </div>
      <div class="span3 customer_service">
        <h4>FREE delivery on ALL orders</h4>
        <h4>
          <small>Customer service: 0800 1234 567</small>
        </h4>
      </div>
    </div>
    <br/>

    <div class="row">
      <div class="links pull-right">
        <a href="/"><i class="icon-home"></i> Home</a> |
        <a href="/account"><i class="icon-user"></i> My Account</a> |
        <a href="/cart"><i class="icon-shopping-cart"></i> Shopping Cart</a> |
        <a href="/about"><i class="icon-info-sign"></i> About</a> |
        <a href="/contact"><i class="icon-envelope"></i> Contact</a>
      <#if model.user?? >
        | <a href="/account/signout"><i class="icon-off"></i> Sign Out</a>
      </#if>
      </div>
    </div>
  </div>
</div>
