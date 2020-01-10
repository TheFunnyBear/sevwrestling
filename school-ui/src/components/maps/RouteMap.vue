<template>
    <div>
        <vl-map data-projection="EPSG:4326" style="height: 400px">
            <vl-view :zoom.sync="zoom" :center.sync="center" :rotation.sync="rotation"></vl-view>

            <vl-layer-tile>
                <vl-source-osm></vl-source-osm>
            </vl-layer-tile>

            <!--Здание ДЮСШ №6 -->
            <vl-feature>
                <vl-geom-circle :coordinates="[33.5159, 44.6045]" :radius="30"></vl-geom-circle>
            </vl-feature>


            <vl-feature>
                <vl-geom-point :coordinates="[33.5159, 44.6045]"></vl-geom-point>
            </vl-feature>

            <vl-feature>
                <vl-geom-line-string :coordinates="[[33.5159, 44.6045],[33.5146, 44.60385]]"></vl-geom-line-string>
            </vl-feature>

            <!--Переход на перекрёстке улицы Частника-->
            <vl-feature>
                <vl-geom-point :coordinates="[33.5146, 44.60385]"></vl-geom-point>
            </vl-feature>

            <vl-feature>
                <vl-geom-line-string :coordinates="[[33.5155, 44.60215], [33.5146, 44.60385]]"></vl-geom-line-string>
            </vl-feature>

            <!--Переход на перекрёстке улицы Адмирала Октябрьского-->
            <vl-feature>
                <vl-geom-point :coordinates="[33.5155, 44.60215]"></vl-geom-point>
            </vl-feature>

            <vl-feature>
                <vl-geom-line-string :coordinates="[[33.5155, 44.60215], [33.51493, 44.60215]]"></vl-geom-line-string>
            </vl-feature>

            <!--Остоновка транспорта 1-->
            <vl-feature>
                <vl-geom-point :coordinates="[33.51493, 44.60215]"></vl-geom-point>
            </vl-feature>


            <vl-feature>
                <vl-geom-line-string :coordinates="[[33.5155, 44.60215], [33.51561, 44.6018]]"></vl-geom-line-string>
            </vl-feature>

            <!--Остоновка транспорта 2-->
            <vl-feature>
                <vl-geom-point :coordinates="[33.51561, 44.6018]"></vl-geom-point>
            </vl-feature>

            <vl-overlay id="overlay" :position="[33.5162, 44.6048]">
                <template slot-scope="scope">
                    <div class="overlay-content">
                        <b>Школа борьбы</b><br>
                        ДЮСШ №6
                    </div>
                </template>
            </vl-overlay>

        </vl-map>
    </div>
</template>

<script>
    export default {
        name: "RouteMap",
        data() {
            return {
                zoom: 16.5,
                center: [33.51563930511469, 44.60294341499633],
                rotation: 0,
            }
        }
    }
</script>

<style scoped>

</style>