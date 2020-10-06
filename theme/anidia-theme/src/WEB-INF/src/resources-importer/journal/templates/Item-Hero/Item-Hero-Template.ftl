<p>${Título.getData()}</p>
<p>${Subtítulo.getData()}</p>
<p>${Botón.getData()}</p>
<a href="${LinkToPage1z94.getFriendlyUrl()}">
	Link-Botón
</a>
<#if Imagen.getData()?? && Imagen.getData() != "">
	<img alt="${Imagen.getAttribute("alt")}" data-fileentryid="${Imagen.getAttribute("fileEntryId")}" src="${Imagen.getData()}" />
</#if>