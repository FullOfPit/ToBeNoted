import {useEffect, useState} from "react";
import axios from "axios";

export default function useAuthentication() {

    const [user, setUser] = useState<{username: string, role: string} | null>(null);

    useEffect(() => {
        (async () => {
            try {
                const response = await axios.get("/api/app-users/me");
                setUser(response.data);
            } catch (error) {
                console.log(error)
            }
        }) ()
    }, [])

    return {user}

}
