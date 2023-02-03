import Ember from 'ember';
import DS from 'ember-data';
import { Projection } from 'ember-flexberry-data';
export let Model = Ember.Mixin.create({
  createTime: DS.attr('date'),
  creator: DS.attr('string'),
  editTime: DS.attr('date'),
  editor: DS.attr('string'),
  objectKey: DS.attr('guid'),
  objectLayerKey: DS.attr('guid'),
  userKey: DS.attr('string'),
  getValidations: function () {
    let parentValidations = this._super();
    let thisValidations = {
      objectKey: { presence: true },
      objectLayerKey: { presence: true }
    };
    return Ember.$.extend(true, {}, parentValidations, thisValidations);
  },
  init: function () {
    this.set('validations', this.getValidations());
    this._super.apply(this, arguments);
  }
});

export function defineNamespace(modelClass) {
  modelClass.reopenClass({
    namespace: 'IIS.RGISPK',
  });
}

export let defineProjections = function (modelClass) {
  modelClass.defineProjection('FavoriteFeaturesL', 'new-platform-flexberry-favorite-feature', {
    objectKey: Projection.attr('Ключ объекта'),
    userKey: Projection.attr('Ключ пользователя')
  });
};