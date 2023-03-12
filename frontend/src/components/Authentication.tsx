import React from "react";
import useAuthentication from "../hooks/useAuthentication";

export default function Authentication({children}:{children: React.ReactNode}) {

    const {user} = useAuthentication();

    //Hide all children if the user is not logged in

    return (
        <div>
            {user ? children : null}
        </div>
    )
}