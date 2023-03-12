import React, {useMemo} from "react";
import useAuthentication from "../hooks/useAuthentication";
import {Navigate, useSearchParams} from "react-router-dom";

export default function NoAuthentication(
    {
        children
    }:{
        children: React.ReactNode
    }) {

    const {user} = useAuthentication();
    const [searchParams] = useSearchParams();
    const redirect = useMemo(
        () => searchParams.get("redirect") || "/", [searchParams]
    )

    return (
        <div>
            {!user ? children : <Navigate to={redirect}/>}
        </div>
    )
}