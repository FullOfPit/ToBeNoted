import {useEffect, useState} from "react";
import {StaffMember} from "../types/StaffMember";
import axios from "axios";

export default function useStaffMembers() {

    const emptyStaffList = [{username: "Aint nobody", eighteenYears: true}];
    const [staffList, setStaffList] = useState<StaffMember[]>(emptyStaffList);

    useEffect(() => {
        (async () => {
            try {
                const response = await axios.get("/api/app-users/staff");
                setStaffList(response.data);
                //TODO
                console.log(response.data)
            } catch (error) {
                //TODO
                console.log(error)
            }
        })()
    }, []);

    const deleteMember = (id: string) => {
        //TODO
        console.log(`To be deleted: ${id}`)
        setStaffList(staffList.filter((member) => member.username !== id));
    }

    const staff: {
        staffList: StaffMember[],
        deleteMember: (id: string) => void,
    } = {
        staffList: staffList,
        deleteMember: deleteMember,
    }

    return (
        {staff}
    )
}