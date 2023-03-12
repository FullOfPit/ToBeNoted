import React from "react";

export default function NoAuthentication({children}:{children: React.ReactNode}) {

    //Show Children is user is not logged in

    return (
        <div>
            {children}
        </div>
    )
}