import React, {useEffect, useState} from "react";
import {StaffMember} from "../types/StaffMember";
import axios from "axios";
import {Stack} from "@mui/material";
import MemberCard from "../components/MemberCard"
import EditButton from "../components/buttons/EditButton";
import DeleteButton from "../components/buttons/DeleteButton";
import AddButton from "../components/buttons/AddButton";

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
        <Stack
            spacing={2}
            justifyContent={"flex-start"}
            alignItems={"center"}
            sx={{paddingTop: 20}}
        >
            {staffList.map((member) =>
                <MemberCard
                    key={member.username}
                    user={member}
                    buttons={[<EditButton/>, <DeleteButton/>]}
                /> )
            }

            <AddButton/>
        </Stack>
    )
}