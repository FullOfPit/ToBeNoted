import {useEffect, useState} from "react";
import {StaffMember} from "../types/StaffMember";
import axios from "axios";

export default function MemberManagement() {

    const [staffList, setStaffList] = useState<StaffMember[]>([{username: "Aint nobody", eighteenYears: true}]);

    useEffect(() => {
        (async () => {
            try {
                const response = await axios.get("/api/app-users/staff");
                setStaffList(response.data);
                console.log(response.data)
            } catch (error) {
                //TODO
                console.log(error)
            }
        })()
    }, []);



    return (
        <div>
            {staffList.map((member) => <p key={member.username}>{member.username}</p>)}
        </div>
    )
}