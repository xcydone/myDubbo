<script>
    export default {
        data() {
            return {}
        },
        props: {
            color: String,
        },
        name: "divTABLE",
        render: function (h) {
            return <div style={this.formateStyle(this.color)}>NIAO!</div>
        },
        methods: {
            formateStyle(val){
                let style = {
                    'width': '100px',
                    'height': '50px'
                }
                style['background-color'] = val
                console.log(style)
                return style
            }
        }
    }
</script>