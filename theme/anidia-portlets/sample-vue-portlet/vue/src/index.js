// import { createApp } from 'vue'
import './installCompositionApi'

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
		`<div>

			<template v-if="(global.state.currentStep === 'funnel')">
				<funnel-view />
			</template>
			<template v-else-if="(global.state.currentStep === 'home') || (global.state.currentStep === 'apartment')">
				<house-view />
			</template>
			<template v-else-if="(global.state.currentStep === 'business')">
				<business-view />
			</template>
            
			<!-- 
			<div>
				<span class="tag">${Liferay.Language.get('portlet-namespace')}:</span> 
				<span class="value">{{portletNamespace}}</span>
			</div>
			<div>
				<span class="tag">${Liferay.Language.get('context-path')}:</span>
				<span class="value">{{contextPath}}</span>
			</div>
			<div>
				<span class="tag">${Liferay.Language.get('portlet-element-id')}:</span>
				<span class="value">{{portletElementId}}</span>
			</div>
			
			<div>
				<span class="tag">${Liferay.Language.get('configuration')}:</span>
				<span class="value pre">{{JSON.stringify(configuration, null, 2)}}</span>
			</div>
			--> 
		</div>`;

	// console.log(vuetify)

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
				portletNamespace, contextPath, portletElementId, configuration
			}
		},
		mounted() {
			console.log("Loading...");
			console.log("GET: /postal-codes");
			house.getPostalCodes();
  	},
	});
}