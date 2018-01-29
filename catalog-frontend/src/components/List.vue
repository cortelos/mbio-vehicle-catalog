<template>
    <div>
        <h3>Available Vehicles</h3>
        <table id="availableVehicles" class="table">
            <thead>
                <tr>
                    <td><strong>Brand</strong></td>
                    <td><strong>Model</strong></td>
                    <td><strong>Fuel Type</strong></td>
                    <td><strong>Hybrid</strong></td>
                    <td></td>
                </tr>
            </thead>
            <tbody>
                <tr
                    v-for="(vehicle, index) in vehicles"
                    :key="vehicle.vehicleId">
                    <td>{{ vehicle.vehicleBrand }}</td>
                    <td>{{ vehicle.vehicleModel }}</td>
                    <td>{{ vehicle.fuelType }}</td>
                    <td>{{ vehicle.hybrid }}</td>
                    <td style="text-align: center"><a ref="#" style="cursor: pointer" @click="removeItem(index)">Remove</a></td>
                </tr>
            </tbody>
        </table>
    </div>
</template>

<script>
    import { eventBus } from '../main.js';

    export default {
        data() {
            return {
                vehicles: []
            }
        },
        methods: {
            loadVehicles() {
                this.resource.getVehicles()
                    .then(response => {
                        return response.json();
                    })
                    .then(data => {
                        this.vehicles.length = 0;
                        for (let key in data) {
                            this.vehicles.push(data[key]);
                        }
                        this.$forceUpdate();
                    });
            },
            removeItem(index) {
                var vehicleToRemove = this.vehicles[index];
                console.log("Removing vehicle %o", vehicleToRemove);
                this.$dialog
                    .confirm('Do you really want to remove '
                        + vehicleToRemove.vehicleBrand + ' '
                        + vehicleToRemove.vehicleModel + '?')
                    .then(() => {
                        this.resource.deleteVehicle({id: vehicleToRemove.vehicleId})
                            .then(response => {
                                if (response.ok) {
                                    this.$toasted.success('Vehicle successfully removed');
                                    this.loadVehicles();
                                } else {
                                    this.$toasted.error('An error occurred while deleting the vehicle');
                                }
                            });
                    })
                    .catch(() => {
                        console.log('Remove aborted');
                    });
            }
        },
        created() {
            eventBus.$on('updateList', () => {
                this.loadVehicles();
            });
            const customActions = {
                getVehicles: {
                    method: 'GET',
                    url: 'vehicles'
                },
                deleteVehicle: {
                    method: 'DELETE',
                    url: 'vehicle{/id}'
                }
            }
            this.resource = this.$resource('', {}, customActions);
        },
        beforeMount: function () {
            this.loadVehicles();
        }
    }
</script>

<style>
    #availableVehicles td {
        width: 25%;
    }
</style>