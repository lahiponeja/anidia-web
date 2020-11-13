import './plugins/compositionApi'
// State Modules
import global from './store/modules/global'
import comparator from './store/modules/comparator'
// Views
import funnelView from './views/funnelView'
import comparatorView from './views/comparatorView'

import Vue from 'vue/dist/vue.common';


/**
 * This is the main entry point of the portlet.
 *
 * See https://tinyurl.com/js-ext-portlet-entry-point for the most recent 
 * information on the signature of this function.
 *
 * @param  {Object} params a hash with values of interest to the portlet
 * @return {void}
 */
export default function main({portletNamespace, contextPath, portletElementId, configuration}) {
    
    const node = document.getElementById(portletElementId);
    
    // Dynamically write markup to portlet's node
    node.innerHTML = /*html*/`
    <div class="an-funnel bg-white pt-xxxl pb-xxxl">
            <div class="an-funnel__titles an-wrapper--sml">
                <p class="an-h6 color-an-theme-dark-grey mb-l">CALCULADORA DE AHORRO</p>
                <p class="an-body-l-bold color-an-theme">Calcula todo lo que podrías ahorrarte instalando gas natural</p>
            </div>
            
            <transition name="view">
                <keep-alive>
                    <component :is="global.activeView().component"></component>
                </keep-alive>
            </transition>
        </div>
    `;
    
    //
    // Use runtime + compiler module in this case so that we don't need to 
    // process templates during build time.
    //
    // See https://vuejs.org/v2/guide/installation.html#Runtime-Compiler-vs-Runtime-only
    // for more information.
    //
    new Vue({
        el: `#${portletElementId}`,
        provide: {
            global,
            comparator,
        },
        components: {
			'funnel-view': funnelView,
			'comparator-view': comparatorView,
        },
		data: {
            global,
            comparator,
		}
	});
    
}