(function($) {
    'use strict';

    /* default config */
    var defaultConfig = {
        /* jsonp service configuration */
        service: {
            /* if true or name - indicates that callback should be used */
            jsonp: false,

            /* timeout in ms */
            timeout: 2000,

            /* data lifetime */
            ttl: 30 * 60 * 1000,

            /* https? */
            secure: false,

            /* service host */
            host: 'api.openweathermap.org',

            /* service port */
            port: 80,

            /* service path */
            path: '/data/2.5/weather',

            /* parameters */
            params: {
            	appid: '96aa3a9f49ea5bd1c62d8340b795158a',
                q: "Delhi,in"
            }
        },

        /* initial values to set in store's storage */
        initialValues: {
            state : 'Delhi'
        }
    };

    /**
     * Store implementation.
     *
     * @extends ContextHub.Store.PersistedJSONPStore
     * @param {String} name - store name
     * @param {Object} config - config
     * @constructor
     */
    var WeatherStore = function(name, config) {
        this.config = $.extend(true, {}, defaultConfig, config);
        this.init(name, this.config);

        /* perform query */
        this.queryService(false);
    };

    /* inherit from ContextHub.Store.SessionStore */
    ContextHub.Utils.inheritance.inherit(WeatherStore, ContextHub.Store.PersistedJSONPStore);

    /**
     * Saves response from the service to the storage.
     *
     * @param {Object} response - raw service response
     */
    WeatherStore.prototype.successHandler = function(response) {

        this.setItem('data', response);
    };

    WeatherStore.prototype.failureHandler = function(error) {
        this.setItem('data', []);
    };

    /* register store candidate */
    ContextHub.Utils.storeCandidates.registerStoreCandidate(WeatherStore, 'weather-store', 0);

}(ContextHubJQ));
