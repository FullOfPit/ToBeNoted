import logoSmall from "../pictures/ToBeNotedSmall.png"

export default function SmallLogo() {

    return (
        <img
            src={logoSmall}
            style={{maxHeight: "50px"}}
            alt={"Small logo of tobenoted app"}
        />
    )
}