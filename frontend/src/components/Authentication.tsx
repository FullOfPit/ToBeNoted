import React from "react";
import useAuthentication from "../hooks/useAuthentication";
import {Navigate, useLocation} from "react-router-dom";

export default function Authentication({children}:{children: React.ReactNode}) {

    const {user, authReady} = useAuthentication();
    const location = useLocation()
    const navigate = <Navigate to={
        `/login?redirect=${encodeURIComponent(location.pathname + location.search)}`
    }/>

    if (authReady && user) {
        return <>{children}</>
    } else if (authReady) {
        return navigate;
    } else {
        return null;
    }
}