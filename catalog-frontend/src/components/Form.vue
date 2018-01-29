<template>
    <div class="container">
        <div class="row">
            <div class="col-xs-12 col-sm-8 col-sm-offset-2 col-md-6 col-md-offset-3">
                <h1>MB.io Vehicle Catalog</h1>
            </div>
        </div>
        <form>
            <div class="row">
                <div class="col-xs-12 col-sm-8 col-sm-offset-2 col-md-6 col-md-offset-3">
                    <h3>Create Vehicle</h3>
                    <br>
                    <div class="form-group">
                        <label>Vehicle Brand</label>
                        <select v-model="vehicle.vehicleBrand" class="form-control">
                            <option v-for="brand in brands" :value="brand">{{ brand }}</option>
                        </select>
                    </div>
                    <div class="form-group">
                        <label>Vehicle Model</label>
                        <input class="form-control" type="text" v-model="vehicle.vehicleModel">
                    </div>
                    <div class="form-group">
                        <label>Vehicle Fuel Type</label>
                        <select v-model="vehicle.fuelType" class="form-control">
                            <option v-for="fuelType in fuelTypes" :value="fuelType">{{ fuelType }}</option>
                        </select>
                    </div>
                </div>
            </div>
            <div class="row">
                <div class="col-xs-12 col-sm-8 col-sm-offset-2 col-md-6 col-md-offset-3">
                    <div class="form-group">
                        <label for="hybrid">
                            <input
                                type="checkbox"
                                id="hybrid"
                                v-model="vehicle.hybrid"> Hybrid
                        </label>
                    </div>
                </div>
            </div>
            <div class="row">
                <div class="col-xs-12 col-sm-8 col-sm-offset-2 col-md-6 col-md-offset-3">
                    <button class="btn btn-primary" @click.prevent="createVehicle">Create</button>
                </div>
            </div>
        </form>
    </div>
</template>

<script>
    import { eventBus } from '../main.js';

    export default {
        data() {
            return {
                vehicle: {
                    vehicleBrand: 'Mercedes-Benz',
                    vehicleModel: '',
                    fuelType: 'Diesel',
                    hybrid: false
                },
                brands: ['Mercedes-Benz', 'Mercedes-Benz AMG', 'smart'],
                fuelTypes: ['Diesel', 'Petrol', 'Electric', 'Hybrid']
            };
        },
        methods: {
            createVehicle() {
                if (this.vehicle.vehicleModel.trim() == '') {
                    this.$toasted.error('The vehicle model must be defined');
                    return;
                }
                console.log("Creating vehicle %o", this.vehicle);
                this.resource.save(this.vehicle)
                    .then(response => {
                            // success callback
                            console.log("Vehicle created");
                            this.$toasted.success('Vehicle successfully created');
                            eventBus.updateList();
                            this.clearForm();
                        },
                        response => {
                            // error callback
                            this.$toasted.error('An error occurred while creating the vehicle: ' + response.statusText);
                        });
                
            },
            clearForm() {
                this.vehicle.vehicleBrand = 'Mercedes-Benz',
                this.vehicle.vehicleModel = '';
                this.vehicle.fuelType = 'Diesel';
                this.vehicle.hybrid = false;
            }
        },
        created() {
            this.resource = this.$resource('vehicle');
        }
    }
</script>