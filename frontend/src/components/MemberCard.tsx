import {StaffMember} from "../types/StaffMember";
import {Button, Card, CardActions, CardContent, Checkbox, Typography} from "@mui/material";
import React from "react";

export default function UserCard(
    {
        user,
        buttons
    }:{
        user: StaffMember
        buttons: {
            icons: React.ReactNode[]
        }
    }) {

    return (
        <Card sx={{ minWidth: 275 }}>
            <CardContent>
                <Typography sx={{ fontSize: 14 }} color="text.secondary" gutterBottom>
                    {user.username}
                </Typography>

                <Typography sx={{ mb: 1.5 }} color="text.secondary">
                    Eighteen Years or older
                </Typography>

                <Checkbox checked={user.eighteenYears}/>
            </CardContent>
            <CardActions>
                <Button size="small">Learn More</Button>
            </CardActions>
        </Card>
    )
}