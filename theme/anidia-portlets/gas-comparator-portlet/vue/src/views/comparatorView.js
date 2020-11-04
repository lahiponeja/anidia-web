import compHotWater from "../components/comparator/compHotWater";
import compHeating from "../components/comparator/compHeating";
import compKitchen from "../components/comparator/compKitchen";
import compSaving from "../components/comparator/compSaving";

const comparatorView = {
  inject: ["comparator"],
  components: {
    'comp-hot-water': compHotWater,
    'comp-heating': compHeating,
    'comp-kitchen': compKitchen,
    'comp-saving': compSaving,
  },
  template: /*html*/`
    <div class="w-full">
      <div class="an-house__steps an-house__steps--calculator an-wrapper an-wrapper--med">  
        <ul class="an-house__steps-list mb-xxxl">
          <template v-for="(compStep, index) in comparator.state.comparatorStepsArr" class="an-house__steps-item">
            <div v-if="compStep.icon" class="an-house__steps-item" :class="{'an-house__steps-item--active': compStep.active }">
              <span class="an-house__steps-item-icon" :class="compStep.icon"></span>
              <p class="an-h5">{{ compStep.name }}</p>
            </div>
          </template>
        </ul>
      </div>

      <transition name="view">
        <keep-alive>
          <component :is="comparator.activeComponent().component"></component>
        </keep-alive>   
      </transition>
    </div>
  `
}

export default comparatorView