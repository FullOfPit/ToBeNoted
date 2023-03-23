import {useCallback, useEffect, useState} from "react";
import {StaffMember} from "../types/StaffMember";
import axios from "axios";

export default function useStaffMembers() {

    const emptyStaffList = [{id: "", username: "Aint nobody", eighteenYears: true}];
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

    const deleteMember = useCallback((id: string) => {
        //TODO abstract it to a generic delete function
        (async () => {
            try {
                await axios.delete(`/api/app-users/staff/${id}`)
                setStaffList(staffList.filter((member) => member.id !== id));
            } catch (error) {
                console.log(error)
            }
        })()
    }, [staffList])



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