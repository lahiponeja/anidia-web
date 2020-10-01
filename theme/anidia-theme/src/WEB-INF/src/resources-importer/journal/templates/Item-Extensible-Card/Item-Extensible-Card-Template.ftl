<div class="an-card an-card--simple">
    <div class="an-card--simple__intro">
        <div class="${Icono.getData()} mb-m"></div>
            <p class="an-body-l-bold an-card__title mb-m">${Título.getData()}</p>
        </div>
        <div class="an-card--simple__text">
            <p class="an-card--simple__text-short an-body-m-regular">
                ${DescripciónCorta.getData()}
            </p>
            
            <#if DescripciónLarga.getData()!="">
                <div class="an-card--simple__text-expanded hide">
                    <p class="mb-m an-menu-bold">${Subtítulo.getData()}</p>
                    <p>${DescripciónLarga.getData()}</p>
                </div>
            </#if>
        </div>

    <#if DescripciónLarga.getData()!="">
        <a href="#" class="an-body-xs-bold an-link" data-expand-card>Leer más</a>
        <div class="an-icon--close-cross hide" data-close-card></div>
    </#if>
</div>