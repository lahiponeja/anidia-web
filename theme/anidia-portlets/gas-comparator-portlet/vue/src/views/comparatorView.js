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
    <div>

      <div class="an-house__steps an-wrapper an-wrapper--med">
        <!-- <div class="an-funnel__titles mb-xl">
          <p class="an-h6 color-an-theme-dark-grey mb-l">{{ houseActiveStep.heading.title }}</p>
          <p class="an-body-l-bold color-an-theme">{{ houseActiveStep.heading.subtitle }}</p>
        </div> -->
  
        <ul class="an-house__steps-list mb-xxxl">
          <template v-for="(compStep, index) in comparator.state.comparatorStepsArr" class="an-house__steps-item">
            <div v-if="compStep.icon" class="an-house__steps-item" :class="{'an-house__steps-item--active': compStep.active }">
              <span class="an-house__steps-item-icon" :class="compStep.icon"></span>
              <p class="an-h5">{{ compStep.name }}</p>
            </div>
          </template>
        </ul>
      </div>


      <keep-alive>
        <component :is="comparator.activeComponent().component"></component>
      </keep-alive>

      <button @click="comparator.changeStepComponent('comp-hot-water')">comp-hot-water</button>
      <br />
      <button @click="comparator.changeStepComponent('comp-heating')">comp-heating</button>
      <br />
      <button @click="comparator.changeStepComponent('comp-kitchen')">comp-kitchen</button>
      <br />
      <button @click="comparator.changeStepComponent('comp-saving')">comp-saving</button>

      <!--
      <comp-hot-water />

      <br />
      <br />

      <comp-heating />

      <br />
      <br />

      <comp-kitchen />

      <br />
      <br />

      <comp-saving />
      -->
      
    </div>
  `
}

export default comparatorView