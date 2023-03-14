import logoBig from "../pictures/ToBeNotedMainCropped.png"

export default function MainLogo() {

    return (
        <>
            <img src={logoBig}
                 alt={"main logo of staff management app called to be noted"}
                 style={{maxWidth: "500px", position:"relative", right: "15px"}}
            />
        </>
    )

}