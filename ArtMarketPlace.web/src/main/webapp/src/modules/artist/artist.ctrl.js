(function (ng) {
    var mod = ng.module('artistModule');

    mod.controller('artistCtrl', ['CrudCreator', '$scope', 'artistService', 'artistModel', function (CrudCreator, $scope, svc, model) {
            CrudCreator.extendController(this, svc, $scope, model, 'artist', 'Artist');
            this.fetchRecords();
        }]);

    mod.controller('artworksCtrl', ['CrudCreator', '$scope', 'artworkModel', function (CrudCreator, $scope, model) {
            CrudCreator.extendCompChildCtrl(this, $scope, model, 'artwork', 'artist');
            this.asGallery = true;
        }]);
})(window.angular);
