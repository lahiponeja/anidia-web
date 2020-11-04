import './plugins/compositionApi'
// State Modules
import global from './store/modules/global'
import house from './store/modules/house'
// Views
import funnelView from './views/funnelView'
import houseView from './views/houseView'
import businessView from './views/businessView'

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
    node.innerHTML = /*html*/
		`<div class="bg-white">
			<transition name="view">
				<keep-alive>
					<component :is="global.activeView().component"></component>
				</keep-alive>
			</transition>
		</div>`;

	new Vue({
		el: `#${portletElementId}`,
		components: {
			'funnel-view': funnelView,
			'house-view': houseView,
			'business-view': businessView,
		},
		provide: {
			global,
			house,
		},
		data() {
			return {
				global,
				house,
				portletNamespace, contextPath, portletElementId, configuration
			}
		},
		created() {
			house.getPostalCodes();
  	},
	});
}