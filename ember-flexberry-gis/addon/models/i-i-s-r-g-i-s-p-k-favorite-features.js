import { Model as FavoriteFeaturesMixin, defineNamespace, defineProjections } from
  '../mixins/regenerated/models/i-i-s-r-g-i-s-p-k-favorite-features';
import { Projection } from 'ember-flexberry-data';
import { Offline } from 'ember-flexberry-data';
let Model = Projection.Model.extend(Offline.ModelMixin, FavoriteFeaturesMixin, {

});

defineNamespace(Model);
defineProjections(Model);
export default Model;