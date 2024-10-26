import { SidebarLayout } from "../../components/pageLayout/SidebarLayout";
import { Button } from "../../components/generic/Button";
import { LabelBar } from "../../components/generic/LabelBar";
import { useFetch } from "../../context/ContextApi";
import { useContext, useEffect, useState } from "react";

export const Environment: React.FC<EnvironmentProps> = () => {
    const [environments, setEvironments] = useState(null);
    const { data, error, fetchData } = useFetch();

    useEffect(() => {
        fetchData("environments");
        console.log("response: ", data);
    }, []);

    return (
        <div>
            <LabelBar label={"Environments"} />
            <SidebarLayout
                leftContent={<div>ae</div>}
                rightContent={
                    <div>
                        <Button content="add +" />
                    </div>
                }
            />
        </div>
    );
};

/* interfaces */
interface EnvironmentProps {}

/* css */
