<#if Estilo.getData() == "verde">
   <div class="an-card an-card--pack an-card--pack--big featured">
   <#else>
     <div class="an-card an-card--pack an-card--pack--big">
</#if>

<div class="an-card--pack__intro">
  <h5>${Nombre.getData()}</h5>
  <h5>${Detalle.getData()}</h5>
</div>
<div class="an-card--pack__info">
  <html>${Titulo.getData()}</html>
</div>
<ul class="an-list">
  <html>${Ventajas.getData()}</html>
</ul>

<#if Estilo.getData() == "verde">
  <a href="${Link.getFriendlyUrl()}" class="an-btn an-btn--flatter an-btn--white">${Boton.getData()}</a>
   <#else>
    <a href="${Link.getFriendlyUrl()}" class="an-btn an-btn--flatter an-btn--gradient">
      <span>${Boton.getData()}</span>
    </a>
</#if>

</div>